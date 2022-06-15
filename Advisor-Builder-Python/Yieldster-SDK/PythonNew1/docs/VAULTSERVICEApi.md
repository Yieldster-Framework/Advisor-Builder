# PythonNew1.openapi_client.VAULTSERVICEApi

All URIs are relative to *http://localhost:8051*

Method | HTTP request | Description
------------- | ------------- | -------------
[**assign_advisor**](VAULTSERVICEApi.md#assign_advisor) | **PUT** /Vault/assignAdvisors/{vaultId} | API to assign an advisor to a vault
[**get_advisor_setting_by_vault_id**](VAULTSERVICEApi.md#get_advisor_setting_by_vault_id) | **GET** /Vault/getAdvisorSettingByVaultId/{vaultId} | API to get vault Advisor settings of a vault
[**get_asset_by_vault_id**](VAULTSERVICEApi.md#get_asset_by_vault_id) | **GET** /Vault/getAssetByVaultId/{id} | API to get asset details of a vault
[**get_invested_protocol_by_vault_id**](VAULTSERVICEApi.md#get_invested_protocol_by_vault_id) | **GET** /Vault/getInvestedProtocolByVaultId/{id} | API to get invested protocol of a vault
[**get_protocols_by_vault_id**](VAULTSERVICEApi.md#get_protocols_by_vault_id) | **GET** /Vault/getProtocolsByVaultId/{id} | API to get supported protocols of a vault
[**get_vault_by_id**](VAULTSERVICEApi.md#get_vault_by_id) | **GET** /Vault/{id} | 


# **assign_advisor**
> SDKResponse assign_advisor(vault_id, advisor_id, request_body)

API to assign an advisor to a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    vault_id = "vaultId_example" # str | VaultId of a particular Vault 
    advisor_id = "advisorId_example" # str | advisorId of a particular advisor 
    request_body = {
        "key": {},
    } # {str: ({str: (bool, date, datetime, dict, float, int, list, str, none_type)},)} | 

    # example passing only required values which don't have defaults set
    try:
        # API to assign an advisor to a vault
        api_response = api_instance.assign_advisor(vault_id, advisor_id, request_body)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->assign_advisor: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vault_id** | **str**| VaultId of a particular Vault  |
 **advisor_id** | **str**| advisorId of a particular advisor  |
 **request_body** | **{str: ({str: (bool, date, datetime, dict, float, int, list, str, none_type)},)}**|  |

### Return type

[**SDKResponse**](SDKResponse.md)

### Authorization

[bearer-jwt](../README.md#bearer-jwt)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Response received |  -  |
**400** | Bad request, check the provided parameters |  -  |
**500** | Internal Server Error |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_advisor_setting_by_vault_id**
> SDKResponse get_advisor_setting_by_vault_id(vault_id)

API to get vault Advisor settings of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    vault_id = "vaultId_example" # str | VaultId of a particular Vault whose strategy details you need to know

    # example passing only required values which don't have defaults set
    try:
        # API to get vault Advisor settings of a vault
        api_response = api_instance.get_advisor_setting_by_vault_id(vault_id)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->get_advisor_setting_by_vault_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **vault_id** | **str**| VaultId of a particular Vault whose strategy details you need to know |

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

# **get_asset_by_vault_id**
> SDKResponse get_asset_by_vault_id(id)

API to get asset details of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    id = "id_example" # str | 

    # example passing only required values which don't have defaults set
    try:
        # API to get asset details of a vault
        api_response = api_instance.get_asset_by_vault_id(id)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->get_asset_by_vault_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**|  |

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

# **get_invested_protocol_by_vault_id**
> SDKResponse get_invested_protocol_by_vault_id(id)

API to get invested protocol of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    id = "id_example" # str | 

    # example passing only required values which don't have defaults set
    try:
        # API to get invested protocol of a vault
        api_response = api_instance.get_invested_protocol_by_vault_id(id)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->get_invested_protocol_by_vault_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**|  |

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

# **get_protocols_by_vault_id**
> SDKResponse get_protocols_by_vault_id(id)

API to get supported protocols of a vault

### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    id = "id_example" # str | 

    # example passing only required values which don't have defaults set
    try:
        # API to get supported protocols of a vault
        api_response = api_instance.get_protocols_by_vault_id(id)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->get_protocols_by_vault_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**|  |

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

# **get_vault_by_id**
> Response get_vault_by_id(id)



### Example

* Bearer (JWT) Authentication (bearer-jwt):

```python
import time
import PythonNew1.openapi_client
from PythonNew1.openapi_client.api import vault_service_api
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
    api_instance = vault_service_api.VAULTSERVICEApi(api_client)
    id = "id_example" # str | 

    # example passing only required values which don't have defaults set
    try:
        api_response = api_instance.get_vault_by_id(id)
        pprint(api_response)
    except PythonNew1.openapi_client.ApiException as e:
        print("Exception when calling VAULTSERVICEApi->get_vault_by_id: %s\n" % e)
```


### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**|  |

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

