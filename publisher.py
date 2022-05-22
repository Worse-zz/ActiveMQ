import time 
import sys
import stomp
from stomp import *
hosts = [('192.168.1.10', 61613)]
conn = stomp.Connection(host_and_ports=hosts)
conn.set_listener('', PrintingListener()) 
conn.connect('admin', 'admin', wait=True)
conn.send(destination='/queue/queue-1', body='Salut')
time.sleep(2)
conn.disconnect()
