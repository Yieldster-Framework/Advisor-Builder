# PythonNew1.openapi_client.SDKSERVICEApi

All URIs are relative to *http://localhost:8051*

Method | HTTP request | Description
------------- | ------------- | -------------
[**execute_strategy**](SDKSERVICEApi.md#execute_strategy) | **GET** /smart-contract/sdk/v2.0/yieldster/execute-strategy | API to execute strategy json
[**get_token_balance**](SDKSERVICEApi.md#get_token_balance) | **GET** /smart-contract/sdk/v2.0/yieldster/tokenBalance | API to get Historical Token Balance of Vault
[**get_token_price**](SDKSERVICEApi.md#get_token_price) | **GET** /smart-contract/sdk/v2.0/yieldster/tokenPrice | API to get Historical Token Price of Vault
[**get_vault_assets**](SDKSERVICEApi.md#get_vault_assets) | **GET** /smart-contract/sdk/v2.0/yieldster/vaultAssets/{vaultAddress} | API to get Assets of a vault
[**get_vault_nav**](SDKSERVICEApi.md#get_vault_nav) | **GET** /smart-contract/sdk/v2.0/yieldster/vaultNAV | API to get Historical NAV of a vault
[**protocol_interaction**](SDKSERVICEApi.md#protocol_interaction) | **GET** /smart-contract/sdk/v2.0/yieldster/protocol_Interaction | API to interact with protocol via python sdk
[**return_json**](SDKSERVICEApi.md#return_json) | **GET** /smart-contract/getJson | 
[**test**](SDKSERVICEApi.md#test) | **GET** /smart-contract/test | 


# **execute_strategy**
> SDKResponse execute_strategy(strategy_plan)

API to execute strategy json

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    strategy_plan = "strategyPlan_example" # str | Strategy plan in JSON format

    # example passing only required values which don't have defaults set
    try:
        # API to execute strategy json
        api_response = api_instance.execute_strategy(strategy_plan)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->execute_strategy: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **strategy_plan** | **str**| Strategy plan in JSON format |

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
**200** | Strategy executed |  -  |
**400** | Bad request, check the provided parameters |  -  |
**500** | Internal Server Error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_token_balance**
> SDKResponse get_token_balance(vault_address, token_address)

API to get Historical Token Balance of Vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    vault_address = "vaultAddress_example" # str | Address of the vault that want to know Token Balance
    token_address = "tokenAddress_example" # str | Address of the Token that want to know Balance 
    timestamp = "timestamp_example" # str | Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  (optional)
    is_date = True # bool | Boolean value which represent the time is in the format date or not  (optional)

    # example passing only required values which don't have defaults set
    try:
        # API to get Historical Token Balance of Vault
        api_response = api_instance.get_token_balance(vault_address, token_address)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_token_balance: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # API to get Historical Token Balance of Vault
        api_response = api_instance.get_token_balance(vault_address, token_address, timestamp=timestamp, is_date=is_date)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_token_balance: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vault_address** | **str**| Address of the vault that want to know Token Balance |
 **token_address** | **str**| Address of the Token that want to know Balance  |
 **timestamp** | **str**| Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  | [optional]
 **is_date** | **bool**| Boolean value which represent the time is in the format date or not  | [optional]

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

# **get_token_price**
> SDKResponse get_token_price(token_address, is_vault_token)

API to get Historical Token Price of Vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    token_address = "tokenAddress_example" # str | Address of the token that want to know Token Price 
    is_vault_token = True # bool | Whether the provided token is a vault token or not
    timestamp = "timestamp_example" # str | Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  (optional)
    is_date = True # bool | Boolean value which represent the time is in the format date or not  (optional)

    # example passing only required values which don't have defaults set
    try:
        # API to get Historical Token Price of Vault
        api_response = api_instance.get_token_price(token_address, is_vault_token)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_token_price: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # API to get Historical Token Price of Vault
        api_response = api_instance.get_token_price(token_address, is_vault_token, timestamp=timestamp, is_date=is_date)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_token_price: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token_address** | **str**| Address of the token that want to know Token Price  |
 **is_vault_token** | **bool**| Whether the provided token is a vault token or not |
 **timestamp** | **str**| Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  | [optional]
 **is_date** | **bool**| Boolean value which represent the time is in the format date or not  | [optional]

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

# **get_vault_assets**
> SDKResponse get_vault_assets(vault_address)

API to get Assets of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    vault_address = "vaultAddress_example" # str | Address of the vault that want to know Assets 

    # example passing only required values which don't have defaults set
    try:
        # API to get Assets of a vault
        api_response = api_instance.get_vault_assets(vault_address)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_vault_assets: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vault_address** | **str**| Address of the vault that want to know Assets  |

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

# **get_vault_nav**
> SDKResponse get_vault_nav(vault_address)

API to get Historical NAV of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    vault_address = "vault address_example" # str | Address of the vault that want to know vault address 
    timestamp = "timestamp_example" # str | Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  (optional)
    is_date = True # bool | Boolean value which represent the time is in the format date or not  (optional)

    # example passing only required values which don't have defaults set
    try:
        # API to get Historical NAV of a vault
        api_response = api_instance.get_vault_nav(vault_address)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_vault_nav: %s\n" % e)

    # example passing only required values which don't have defaults set
    # and optional values
    try:
        # API to get Historical NAV of a vault
        api_response = api_instance.get_vault_nav(vault_address, timestamp=timestamp, is_date=is_date)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->get_vault_nav: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vault_address** | **str**| Address of the vault that want to know vault address  |
 **timestamp** | **str**| Unix timestamp or date in the format DD-MM-YYYY HH:MM:SS  | [optional]
 **is_date** | **bool**| Boolean value which represent the time is in the format date or not  | [optional]

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

# **protocol_interaction**
> SDKResponse protocol_interaction(str)

API to interact with protocol via python sdk

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)
    str = "str_example" # str | Object that contains details like vault address,pool address, encoded instruction, amount, from-token and to-token addresses.Keys needed: VAULT_ADDRESS, POOL_ADDRESS, INSTRUCTION, AMOUNT, FROM_TOKEN, RETURN_TOKEN

    # example passing only required values which don't have defaults set
    try:
        # API to interact with protocol via python sdk
        api_response = api_instance.protocol_interaction(str)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->protocol_interaction: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **str** | **str**| Object that contains details like vault address,pool address, encoded instruction, amount, from-token and to-token addresses.Keys needed: VAULT_ADDRESS, POOL_ADDRESS, INSTRUCTION, AMOUNT, FROM_TOKEN, RETURN_TOKEN |

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
**200** | Protocol interaction operation executed |  -  |
**400** | Bad request, check the provided parameters |  -  |
**500** | Internal Server Error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **return_json**
> str return_json()



### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)

    # example, this endpoint has no required or optional parameters
    try:
        api_response = api_instance.return_json()
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->return_json: %s\n" % e)
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
**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **test**
> test()



### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import sdk_service_api
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
    api_instance = sdk_service_api.SDKSERVICEApi(api_client)

    # example, this endpoint has no required or optional parameters
    try:
        api_instance.test()
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling SDKSERVICEApi->test: %s\n" % e)
```


### Parameters
This endpoint does not need any parameter.

### Return type

void (empty response body)

### Authorization

[bearer-jwt](../README.md#bearer-jwt)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

