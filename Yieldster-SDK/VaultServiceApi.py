import time
import PythonNew1.openapi_client
from pprint import pprint
from PythonNew1.openapi_client.api import sdk_service_api
from PythonNew1.openapi_client.api import vault_service_api

# Defining the host is optional and defaults to http://localhost:8051
# See configuration.py for a list of all supported configuration parameters.
class vaultApi:
    def __int__(self):
        self.configuration = PythonNew1.openapi_client.Configuration(
            host="http://localhost:8051"
        )
        with PythonNew1.openapi_client.ApiClient(self.configuration) as api_client:
            # Create an instance of the API class
            api_instance = vault_service_api.VAULTSERVICEApi(api_client)

    # The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

# Configure Bearer authorization (JWT): bearer-jwt
# configuration = PythonNew1.openapi_client.Configuration(
#     access_token='YOUR_BEARER_TOKEN'
# )

# Enter a context with an instance of the API client


    # def assign_advisor(vault_id, advisor_id, request_body):
    #     api_response = api_instance.assign_advisor(vault_id, advisor_id, request_body, async_req=True)
    #     return api_response.get()


    def get_vault_by_id(self,vault_id):
        api_response = self.api_instance.get_vault_by_id(vault_id, async_req=True)
        return api_response.get()


    def get_protocol_by_vault_id(self,vault_id):
        api_response = self.api_instance.get_protocols_by_vault_id(vault_id, async_req=True)
        return api_response.get()


    def get_invested_protocol_by_vault_id(self,vault_id):
        api_response = self.api_instance.get_invested_protocol_by_vault_id(vault_id, async_req=True)
        return api_response.get()


    def get_invested_protocol_by_vault_id(self,vault_id):
        api_response = self.api_instance.get_invested_protocol_by_vault_id(vault_id, async_req=True)
        return api_response.get()


    def get_asset_by_vault_id(self,vault_id):
        api_response = self.api_instance.get_asset_by_vault_id(vault_id, async_req=True)
        return api_response.get()


    def get_advisor_setting_by_vault_id(self,vault_id):
        api_response = self.api_instance.get_advisor_setting_by_vault_id(vault_id, async_req=True)
        return api_response.get()


