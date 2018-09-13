# Towards a Framework for Generating Program Dependence Graphs from Source Code

Originally conceived for compiler optimization, the program dependence graph has become a widely used internal representation for tools in many software engineering tasks. The currently available frameworks for building program dependence graphs rely on compiled source code, which requires resolving dependencies. As a result, these frameworks cannot be applied for analyzing legacy codebases whose dependencies cannot be automatically resolved, or for large codebases in which resolving dependencies can be infeasible. In this paper, we present a framework for generating program dependence graphs from source code based on transition rules, and we describe lessons learned when implementing two different versions of the framework based on a [grammar interpreter](https://github.com/victorjmarin/sourcedg/tree/interpreter) and an [abstract syntax tree iterator](https://github.com/victorjmarin/sourcedg/tree/ast-rules), respectively.
