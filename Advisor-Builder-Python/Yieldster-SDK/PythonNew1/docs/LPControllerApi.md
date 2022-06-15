# PythonNew1.openapi_client.LPControllerApi

All URIs are relative to *http://localhost:8051*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_pool_by_asset**](LPControllerApi.md#get_pool_by_asset) | **GET** /lp-controller/common/get-pool-by-asset | 


# **get_pool_by_asset**
> Response get_pool_by_asset(asset_list)



### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import lp_controller_api
from PythonNew1.openapi_client.model.response import Response
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
    api_instance = lp_controller_api.LPControllerApi(api_client)
    asset_list = [
        "assetList_example",
    ] # [str] | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.get_pool_by_asset(asset_list)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling LPControllerApi->get_pool_by_asset: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **asset_list** | **[str]**|  |

### Return type

[**Response**](Response.md)

### Authorization

[bearer-jwt](../README.md#bearer-jwt)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

