import json
from pprint import pprint
from flask import Flask

from SdkServiceApi import sdkApi
from Service.Impl.HelloWorldImpl import HelloWorldImpl    
    
app = Flask(__name__)
@app.route('/', methods = ['GET'])
def getAdvisorJson():  
        Hello = HelloWorldImpl()
        return Hello.generate_maximize_asset_return()

if __name__ == '__main__':
   app.run()
