import time 
import sys
import stomp
from stomp import *
hosts = [('192.168.1.10', 61613)]
conn = stomp.Connection(host_and_ports=hosts)
conn.set_listener('', PrintingListener()) 
conn.connect('admin', 'admin', wait=True)
conn.subscribe(destination='/queue/queue-1', id=1, ack='auto')
while 1:
  time.sleep(2)
conn.disconnect()
