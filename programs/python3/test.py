def assignment1(a):
  i = 0
  o = 0
  e = 1

  while i < len(a):
    if i % 2 == 1:
      o += a[i]

    if i % 2 == 0:
      e *= a[i]
    i += 1

  print (str(o)+','+str(e))
  
def f1():
  a = 8