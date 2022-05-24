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
### Sample advisor

In the below sample advisor, the protocol address to which the vault is currently invested in is obtained using vaultServiceApi.getInvestedProtocolByVaultId(vaultId) function and an advisor instruction to invest the current investable assets (Ad) present in the vault into the current invested protocol. The function takes vaultId as an input parameter and returns the generated instruction JSON as string.

```java
public String investToProtocol(String vaultId) throws JsonProcessingException {
        SDKResponse responseData = vaultServiceApi.getInvestedProtocolByVaultId(vaultId);
        if (responseData.getStatusCode() == 200) {
            String investedProtocolAddress = (String) responseData.getData();
            Advisor advisor = Advisor.builder()
                    .type("Sample_Advisor")
                    .steps(ImmutableList.of(
                            MoveStep.builder()
                                    .type("MoveStep")
                                    .fromAsset("Ad")
                                    .toAsset(investedProtocolAddress)
                                    .build())).build();
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(advisor);
        } else return null;
    }
```