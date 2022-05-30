# PythonNew1.openapi_client.PATHEXECUTIONSERVICEApi

All URIs are relative to *http://localhost:8051*

Method | HTTP request | Description
------------- | ------------- | -------------
[**check_for_direct_paths**](PATHEXECUTIONSERVICEApi.md#check_for_direct_paths) | **GET** /path-execution/v2.0/yieldster/check-direct-paths | API to check whether direct path exist
[**execute_strategy_path**](PATHEXECUTIONSERVICEApi.md#execute_strategy_path) | **GET** /path-execution/v2.0/yieldster/execute-strategy-path | API to execute strategy path
[**fetch_all_paths**](PATHEXECUTIONSERVICEApi.md#fetch_all_paths) | **GET** /path-execution/v2.0/yieldster/fetch-all-paths | API to get all available execution paths
[**fetch_direct_graph**](PATHEXECUTIONSERVICEApi.md#fetch_direct_graph) | **GET** /path-execution/v2.0/yieldster/fetch-direct-paths | API to get all direct paths from start token to end token without swapping into any intermediate tokens
[**find_paths**](PATHEXECUTIONSERVICEApi.md#find_paths) | **GET** /path-execution/v2.0/yieldster/fetch-particular-paths | API to get paths that lead us from start token to end token


# **check_for_direct_paths**
> SDKResponse check_for_direct_paths(from_token, to_token)

API to check whether direct path exist

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import path_execution_service_api
from PythonNew1.openapi_client.model.sdk_response import SDKResponse
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
    api_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)
    from_token = "fromToken_example" # str | Symbol of the initial token that you are providing for execution
    to_token = "toToken_example" # str | Symbol of the final token that you wish to receive at the end of the execution

    # example passing only required values which don't have defaults set
    try:
        # API to check whether direct path exist
        api_response = api_instance.check_for_direct_paths(from_token, to_token)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling PATHEXECUTIONSERVICEApi->check_for_direct_paths: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from_token** | **str**| Symbol of the initial token that you are providing for execution |
 **to_token** | **str**| Symbol of the final token that you wish to receive at the end of the execution |

### Return type

[**SDKResponse**](SDKResponse.md)

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

# **execute_strategy_path**
> SDKResponse execute_strategy_path(path_id, amount, vault_address, from_token_address, to_token_address)

API to execute strategy path

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import path_execution_service_api
from PythonNew1.openapi_client.model.sdk_response import SDKResponse
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
    api_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)
    path_id = "pathId_example" # str | Id of the path which you wish to execute
    amount = 1 # int | amount of the initial token that you plan to provide for execution
    vault_address = "vaultAddress_example" # str | Address of the vault through which we need to initiate the execution
    from_token_address = "fromTokenAddress_example" # str | Address of the initial token
    to_token_address = "toTokenAddress_example" # str | Address of the final token

    # example passing only required values which don't have defaults set
    try:
        # API to execute strategy path
        api_response = api_instance.execute_strategy_path(path_id, amount, vault_address, from_token_address, to_token_address)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling PATHEXECUTIONSERVICEApi->execute_strategy_path: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **path_id** | **str**| Id of the path which you wish to execute |
 **amount** | **int**| amount of the initial token that you plan to provide for execution |
 **vault_address** | **str**| Address of the vault through which we need to initiate the execution |
 **from_token_address** | **str**| Address of the initial token |
 **to_token_address** | **str**| Address of the final token |

### Return type

[**SDKResponse**](SDKResponse.md)

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

# **fetch_all_paths**
> SDKResponse fetch_all_paths()

API to get all available execution paths

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import path_execution_service_api
from PythonNew1.openapi_client.model.sdk_response import SDKResponse
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
    api_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)

    # example, this endpoint has no required or optional parameters
    try:
        # API to get all available execution paths
        api_response = api_instance.fetch_all_paths()
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling PATHEXECUTIONSERVICEApi->fetch_all_paths: %s\n" % e)
```


### Parameters
This endpoint does not need any parameter.

### Return type

[**SDKResponse**](SDKResponse.md)

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

# **fetch_direct_graph**
> SDKResponse fetch_direct_graph(from_token_amount, from_token, to_token)

API to get all direct paths from start token to end token without swapping into any intermediate tokens

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import path_execution_service_api
from PythonNew1.openapi_client.model.sdk_response import SDKResponse
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
    api_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)
    from_token_amount = "fromTokenAmount_example" # str | amount of the initial token that you plan to provide for execution
    from_token = "fromToken_example" # str | Symbol of the initial token that you are providing for execution
    to_token = "toToken_example" # str | Symbol of the final token that you wish to receive at the end of the execution

    # example passing only required values which don't have defaults set
    try:
        # API to get all direct paths from start token to end token without swapping into any intermediate tokens
        api_response = api_instance.fetch_direct_graph(from_token_amount, from_token, to_token)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling PATHEXECUTIONSERVICEApi->fetch_direct_graph: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from_token_amount** | **str**| amount of the initial token that you plan to provide for execution |
 **from_token** | **str**| Symbol of the initial token that you are providing for execution |
 **to_token** | **str**| Symbol of the final token that you wish to receive at the end of the execution |

### Return type

[**SDKResponse**](SDKResponse.md)

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

# **find_paths**
> SDKResponse find_paths(from_token_amount, from_token, to_token)

API to get paths that lead us from start token to end token

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import path_execution_service_api
from PythonNew1.openapi_client.model.sdk_response import SDKResponse
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
    api_instance = path_execution_service_api.PATHEXECUTIONSERVICEApi(api_client)
    from_token_amount = "fromTokenAmount_example" # str | amount of the initial token that you plan to provide for execution
    from_token = "fromToken_example" # str | Symbol of the initial token that you are providing for execution
    to_token = "toToken_example" # str | Symbol of the final token that you wish to receive at the end of the execution

    # example passing only required values which don't have defaults set
    try:
        # API to get paths that lead us from start token to end token
        api_response = api_instance.find_paths(from_token_amount, from_token, to_token)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling PATHEXECUTIONSERVICEApi->find_paths: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **from_token_amount** | **str**| amount of the initial token that you plan to provide for execution |
 **from_token** | **str**| Symbol of the initial token that you are providing for execution |
 **to_token** | **str**| Symbol of the final token that you wish to receive at the end of the execution |

### Return type

[**SDKResponse**](SDKResponse.md)

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

