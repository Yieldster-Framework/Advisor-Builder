import time

import null

from PythonNew1 import openapi_client
from pprint import pprint
from PythonNew1.openapi_client.api import sdk_service_api
from config import Config


class sdkApi:

    # Defining the host is optional and defaults to http://localhost:8051
    # See configuration.py for a list of all supported configuration parameters.
    def __init__(self):
        # self.jwt_token = ""
        configuration = openapi_client.Configuration(
            host= Config.JAVA_API_URL
            # access_token=jwt_token
        )

        with openapi_client.ApiClient(configuration) as api_client:
            self.api_instance = sdk_service_api.SDKSERVICEApi(api_client)
            # self.graph_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)

    # The client must configure the authentication and authorization parameters
    # in accordance with the API server security policy.
    # Examples for each auth method are provided below, use the example that
    # satisfies your auth use case.

    # Configure Bearer authorization (JWT): bearer-jwt
    # configuration = PythonNew1.openapi_client.Configuration(
    #     access_token='YOUR_BEARER_TOKEN'
    # )

    # Enter a context with an instance of the API client
    # def config(self):
    #     with PythonNew1.openapi_client.ApiClient(self.configuration) as api_client:
    #         # Create an instance of the API class
    #         self.api_instance = sdk_service_api.SDKSERVICEApi(api_client)

    # def get_vault_nav(vault_address):
    #     api_response = api_instance.get_vault_nav(vault_address, async_req=True)
    #     return api_response.get()

    def get_vault_nav(self, vault_address,**kwargs):
        if kwargs.__len__() == 0:
            api_response = self.api_instance.get_vault_nav(vault_address,async_req=True)
        else:
            api_response = self.api_instance.get_vault_nav(vault_address, timestamp=kwargs['time_stamp'], is_date=kwargs['is_date'],
                                                       async_req=True)
        return api_response.get()

    def get_vault_asset(self, vault_address):
        api_response = self.api_instance.get_vault_assets(vault_address, async_req=True)
        return api_response.get()

    def get_token_price(self, token_address, is_vault_token,**kwargs):
        if kwargs.__len__()==0:
            api_response = self.api_instance.get_token_price(token_address, is_vault_token=is_vault_token,async_req=True)
        else:
            api_response = self.api_instance.get_token_price(token_address, is_vault_token=is_vault_token,
                                                         timestamp=kwargs['time_stamp'], is_date=kwargs['is_date'], async_req=True)
        return api_response.get()

    def get_token_balance(self, vault_address, token_address,**kwargs):
        if kwargs.__len__() == 0:
            api_response = self.api_instance.get_token_balance(vault_address, token_address,async_req=True)
        else:
            api_response = self.api_instance.get_token_balance(vault_address, token_address,      timestamp=kwargs['time_stamp'], is_date=kwargs['is_date'], async_req=True)
        return api_response.get()

        # def protocol_interaction(protocol_interaction):
        #     api_response = api_instance.protocol_interaction(protocol_interaction, async_req=True)
        #     return api_response.get()
        #
        #
        # def execute_strategy(strategy_json):
        #     api_response = api_instance.execute_strategy(strategy_json, async_req=True)
        #     return api_response.get()
