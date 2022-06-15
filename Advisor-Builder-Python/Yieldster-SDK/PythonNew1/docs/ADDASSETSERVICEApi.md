# PythonNew1.openapi_client.ADDASSETSERVICEApi

All URIs are relative to *http://localhost:8051*

Method | HTTP request | Description
------------- | ------------- | -------------
[**add_asset_list**](ADDASSETSERVICEApi.md#add_asset_list) | **POST** /asset/v2.0/yieldster/add-assetlist | API to execute strategy path


# **add_asset_list**
> str add_asset_list()

API to execute strategy path

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import add_asset_service_api
from pprint import pprint
# Defining the host is optional and defaults to http://localhost:8051
# See configuration.py for a list of all supported configuration parameters.
configuration = PythonNew1.openapi_client.Configuration(
    host = "http://localhost:8051"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

# Configure Bearer authorization (JWT): bearer-jwt
configuration = PythonNew1.openapi_client.Configuration(
    access_token = 'YOUR_BEARER_TOKEN'
)

# Enter a context with an instance of the API client
with PythonNew1.openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = add_asset_service_api.ADDASSETSERVICEApi(api_client)

    # example, this endpoint has no required or optional parameters
    try:
        # API to execute strategy path
        api_response = api_instance.add_asset_list()
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling ADDASSETSERVICEApi->add_asset_list: %s\n" % e)
```


### Parameters
This endpoint does not need any parameter.

### Return type

**str**

### Authorization

[bearer-jwt](../README.md#bearer-jwt)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Response received |  -  |
**400** | Bad request, check the provided parameters |  -  |
**500** | Internal Server Error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

