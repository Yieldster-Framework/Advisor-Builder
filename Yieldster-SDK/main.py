import json
from pprint import pprint

from SdkServiceApi import sdkApi
from Service.Impl.HelloWorldImpl import HelloWorldImpl

if __name__ == '__main__':
    Hello = HelloWorldImpl()
    print(Hello.createAdvisorMaximizeAPR())
    # print(sdk.get_token_price("0xdAC17F958D2ee523a2206206994597C13D831ec7",is_vault_token="false"))
    # print(sdk.get_vault_asset("0x8e452e4391b6A5C479aBF0290C680371C26bde9b"))
    print(Hello.generate_maximize_asset_return())
