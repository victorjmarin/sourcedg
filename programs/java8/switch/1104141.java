package cz.fi.muni.xkremser.editor.server.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import cz.fi.muni.xkremser.editor.client.util.Constants;
import cz.fi.muni.xkremser.editor.server.exception.DatabaseException;
import cz.fi.muni.xkremser.editor.shared.rpc.TreeStructureBundle.TreeStructureInfo;
import cz.fi.muni.xkremser.editor.shared.rpc.TreeStructureBundle.TreeStructureNode;

/**
 * @author Jiri Kremser
 * @version 25. 1. 2011
 */
public class TreeStructureDAOImpl extends AbstractDAO implements TreeStructureDAO {

    public static final String SELECT_INFOS = "SELECT eu.surname || ', ' || eu.name as full_name, ts.* FROM " + Constants.TABLE_TREE_STRUCTURE_NAME + " ts LEFT JOIN " + Constants.TABLE_EDITOR_USER + " eu ON eu.id = ts.user_id";

    public static final String SELECT_INFOS_BY_USER = SELECT_INFOS + " WHERE eu.id = (?)";

    public static final String SELECT_INFOS_BY_USER_AND_CODE = SELECT_INFOS_BY_USER + " AND ts.barcode = (?)";

    public static final String SELECT_NODES = "SELECT * FROM " + Constants.TABLE_TREE_STRUCTURE_NODE_NAME + " WHERE tree_id = (?)";

    public static final String DELETE_NODES = "DELETE FROM " + Constants.TABLE_TREE_STRUCTURE_NODE_NAME + " WHERE tree_id = (?)";

    public static final String DELETE_INFO = "DELETE FROM " + Constants.TABLE_TREE_STRUCTURE_NAME + " WHERE id = (?)";

    public static final String INSERT_INFO = "INSERT INTO " + Constants.TABLE_TREE_STRUCTURE_NAME + " (user_id, created, description, barcode, name, input_path, model) VALUES ((?), (CURRENT_TIMESTAMP), (?), (?), (?), (?), (?))";

    public static final String INFO_VALUE = "SELECT currval('" + Constants.SEQUENCE_TREE_STRUCTURE + "')";

    public static final String INSERT_NODE = "INSERT INTO " + Constants.TABLE_TREE_STRUCTURE_NODE_NAME + " (tree_id, prop_id, prop_parent, prop_name, prop_picture, prop_type, prop_type_id, prop_page_type, prop_date_issued, prop_alto_path, prop_ocr_path, prop_exist) VALUES ((?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?), (?))";

    private static final Logger LOGGER = Logger.getLogger(TreeStructureDAOImpl.class);

    private static enum DISCRIMINATOR {

        ALL, ALL_OF_USER, BARCODE_OF_USER
    }

    /**
     * {@inheritDoc}
     * 
     * @throws DatabaseException
     */
    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructuresOfUser(long userId) throws DatabaseException {
        return getSavedStructures(DISCRIMINATOR.ALL_OF_USER, userId, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TreeStructureInfo> getAllSavedStructures() throws DatabaseException {
        return getSavedStructures(DISCRIMINATOR.ALL, -1, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TreeStructureInfo> getSavedStructuresOfUser(long userId, String code) throws DatabaseException {
        return getSavedStructures(DISCRIMINATOR.BARCODE_OF_USER, userId, code);
    }

    private ArrayList<TreeStructureInfo> getSavedStructures(DISCRIMINATOR what, long userId, String code) throws DatabaseException {
        PreparedStatement selectSt = null;
        ArrayList<TreeStructureInfo> retList = new ArrayList<TreeStructureInfo>();
        try {
            switch(what) {
                case ALL:
                    selectSt = getConnection().prepareStatement(SELECT_INFOS);
                    break;
                case ALL_OF_USER:
                    selectSt = getConnection().prepareStatement(SELECT_INFOS_BY_USER);
                    selectSt.setLong(1, userId);
                    break;
                case BARCODE_OF_USER:
                    selectSt = getConnection().prepareStatement(SELECT_INFOS_BY_USER_AND_CODE);
                    selectSt.setLong(1, userId);
                    selectSt.setString(2, code);
                    break;
                default:
                    throw new IllegalStateException(what.toString());
            }
        } catch (SQLException e) {
            LOGGER.error("Could not get select infos statement", e);
        }
        try {
            ResultSet rs = selectSt.executeQuery();
            while (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = rs.getTimestamp("created");
                if (date != null) {
                    retList.add(new TreeStructureInfo(rs.getLong("id"), formatter.format(date), rs.getString("description"), rs.getString("barcode"), rs.getString("name"), rs.getString("full_name"), rs.getString("input_path"), rs.getString("model")));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("Query: " + selectSt, e);
        } finally {
            closeConnection();
        }
        return retList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeSavedStructure(long id) throws DatabaseException {
        PreparedStatement deleteSt1 = null, deleteSt2 = null;
        try {
            deleteSt1 = getConnection().prepareStatement(DELETE_NODES);
            deleteSt1.setLong(1, id);
            deleteSt1.executeUpdate();
            deleteSt2 = getConnection().prepareStatement(DELETE_INFO);
            deleteSt2.setLong(1, id);
            deleteSt2.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Could not delete tree structure (info) with id " + id + "\n Query1: " + deleteSt1 + "\n Query2: " + deleteSt2, e);
        } finally {
            closeConnection();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ArrayList<TreeStructureNode> loadStructure(long structureId) throws DatabaseException {
        PreparedStatement selectSt = null;
        ArrayList<TreeStructureNode> retList = new ArrayList<TreeStructureNode>();
        try {
            selectSt = getConnection().prepareStatement(SELECT_NODES);
            selectSt.setLong(1, structureId);
        } catch (SQLException e) {
            LOGGER.error("Could not get select nodes statement", e);
        }
        try {
            ResultSet rs = selectSt.executeQuery();
            while (rs.next()) {
                retList.add(new TreeStructureNode(rs.getString("prop_id"), rs.getString("prop_parent"), rs.getString("prop_name"), rs.getString("prop_picture"), rs.getString("prop_type"), rs.getString("prop_type_id"), rs.getString("prop_page_type"), rs.getString("prop_date_issued"), rs.getString("prop_alto_path"), rs.getString("prop_ocr_path"), rs.getBoolean("prop_exist")));
            }
        } catch (SQLException e) {
            LOGGER.error("Query: " + selectSt, e);
        } finally {
            closeConnection();
        }
        return retList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveStructure(long userId, TreeStructureInfo info, List<TreeStructureNode> structure) throws DatabaseException {
        if (info == null) throw new NullPointerException("info");
        if (structure == null) throw new NullPointerException("structure");
        try {
            getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.warn("Unable to set autocommit off", e);
        }
        PreparedStatement insertInfoSt = null, insSt = null;
        try {
            insertInfoSt = getConnection().prepareStatement(INSERT_INFO);
            insertInfoSt.setLong(1, userId);
            insertInfoSt.setString(2, info.getDescription() != null ? info.getDescription() : "");
            insertInfoSt.setString(3, info.getBarcode());
            insertInfoSt.setString(4, info.getName());
            insertInfoSt.setString(5, info.getInputPath());
            insertInfoSt.setString(6, info.getModel());
            insertInfoSt.executeUpdate();
            PreparedStatement seqSt = getConnection().prepareStatement(INFO_VALUE);
            ResultSet rs = seqSt.executeQuery();
            int key = -1;
            while (rs.next()) {
                key = rs.getInt(1);
            }
            if (key == -1) {
                getConnection().rollback();
                throw new DatabaseException("Unable to obtain new id from DB when executing query: " + insertInfoSt);
            }
            int total = 0;
            for (TreeStructureNode node : structure) {
                insSt = getConnection().prepareStatement(INSERT_NODE);
                insSt.setLong(1, key);
                insSt.setString(2, node.getPropId());
                insSt.setString(3, node.getPropParent());
                insSt.setString(4, node.getPropName());
                insSt.setString(5, node.getPropPicture());
                insSt.setString(6, node.getPropType());
                insSt.setString(7, node.getPropTypeId());
                insSt.setString(8, node.getPropPageType());
                insSt.setString(9, node.getPropDateIssued());
                insSt.setString(10, node.getPropAltoPath());
                insSt.setString(11, node.getPropOcrPath());
                insSt.setBoolean(12, node.getPropExist());
                total += insSt.executeUpdate();
            }
            if (total != structure.size()) {
                getConnection().rollback();
                throw new DatabaseException("Unable to insert _ALL_ nodes: " + total + " nodes were inserted of " + structure.size());
            }
            getConnection().commit();
        } catch (SQLException e) {
            LOGGER.error("Queries: \"" + insertInfoSt + "\" and \"" + insSt + "\"", e);
        } finally {
            closeConnection();
        }
    }
}
