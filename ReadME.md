# **Steps to Create an Advisor using Advisor Builder**


The repository consists of 2 modules advisorBuilder & sampleProject and yieldster-client jar file. The advisor developer can clone the advisorBuilder module to create new advisors.  The yieldster-client jar file provided has to be added as a dependency library into the advisorBuilder module so as to access the function that provide various information of the vaults.

The ***service/Impl/HelloWorldImpl*** class in sampleProject can be used as reference for the purpose of advisor creation, which includes sample functions to get vault meta-data & also the creation of a sample advisor.

_SdkServiceApi_ and _VaultServiceApi_ bean objects can be used to get details of a vault. 

```java
//Sample function get assets supported by a vault

vaultServiceApi.getAssetByVaultId(vaultId)
```

```yaml
# configuration in application.yml file
client:
  host:
    uri: url of backend host
```
