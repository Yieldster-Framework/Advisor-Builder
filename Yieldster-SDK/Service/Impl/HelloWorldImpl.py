import json

import null

from Models.Advisor import Advisor
from Models.ConditionalStep import ConditionalStep
from Models.MoveStep import MoveStep

from SdkServiceApi import sdkApi


# @zope.interface.implementer(Service.HelloWorldService)
class HelloWorldImpl:
    # def __int__(self):
    #     self.sdkServiceApi = sdkApi()

    def get_vault_nav(self, vault_address):
        response = self.sdkServiceApi.get_vault_nav(vault_address=vault_address)
        return response

    def get_vault_asset(self, vault_address):
        response = self.sdkServiceApi.get_vault_asset(vault_address=vault_address)
        return response

    def get_token_price(self, vault_address, is_vault):
        response = self.sdkServiceApi.get_token_price(token_address=vault_address, is_vault_token=is_vault)
        return response

    def generate_maximize_asset_return(self):
        sdk = sdkApi()

        yvCurve_FRAX = "0xB4AdA607B9d6b2c9Ee07A275e9616B84AC560139";
        currentTokenData1 = sdk.get_token_price(token_address="0xdAC17F958D2ee523a2206206994597C13D831ec7",
                                                is_vault_token=False,time_stamp=None,is_date=None)
        previousTokenData1 = sdk.get_token_price(token_address="0xdAC17F958D2ee523a2206206994597C13D831ec7", is_vault_token=False,
                                                               time_stamp="1650737071", is_date=False)
        print(previousTokenData1)
        currentTokenData = {
            "data": {
                "Block Number": 14827486,
                "Token Price": 1000836930000000000,
                "Timestamp": 1653291229
            },
            "message": "Token Value in USD",
            "transactionHash": None,
            "error": None,
            "statusCode": 200
        }
        previousTokenData = {
            "data": {
                "Block Number": 14827486,
                "Token Price": 100083693000000,
                "Timestamp": 1653291229
            },
            "message": "Token Value in USD",
            "transactionHash": None,
            "error": None,
            "statusCode": 200
        }
        if currentTokenData['statusCode'] == 200 and previousTokenData['statusCode'] == 200:
            currentTokenDataMap = currentTokenData['data']
            previousTokenDataMap = previousTokenData['data']
            currentTokenPrice = currentTokenDataMap['Token Price']
            previousTokenPrice = previousTokenDataMap['Token Price']
            priceDifferencePercentage = ((currentTokenPrice - previousTokenPrice) / previousTokenPrice) * 100
            if priceDifferencePercentage > 0.5:
                advisor = Advisor()
                advisor.advisorType = "Sample_Advisor"
                movestep = MoveStep()
                movestep.toAsset = yvCurve_FRAX
                movestep.fromAsset = "AD"
                advisor.steps.append(json.dumps(movestep.__dict__))
                # print(json.dumps(advisor.__dict__))
                return advisor.__dict__
        return None

    def createAdvisorMaximizeAPR(self):
        advisor = Advisor()
        advisor.advisorType = "Maximize_APR"
        conditional_step1 = ConditionalStep()
        conditional_step1.conditionalStatement = "AD >= TMin"
        conditional_step2 = ConditionalStep()
        move_step1 = MoveStep()
        conditional_step2.conditionalStatement = "APRMax <= APRInv"
        move_step1.fromAsset = "AD"
        move_step1.toAsset = "APInv"
        conditional_step2.trueExecuteStep.append(move_step1.__dict__)
        conditional_step3 = ConditionalStep()
        conditional_step3.conditionalStatement = "IDMax * BTotal > Pin + Pout - Gas"
        move_step1.fromAsset = "(APInv + AD)"
        move_step1.toAsset = "APMax"
        conditional_step3.trueExecuteStep.append(move_step1.__dict__)
        move_step1.fromAsset = "AD"
        move_step1.toAsset = "APInv"
        conditional_step3.falseExecuteStep.append(move_step1.__dict__)
        conditional_step2.falseExecuteStep.append(conditional_step3.__dict__)
        conditional_step1.trueExecuteStep.append(conditional_step2.__dict__)
        conditional_step1.falseExecuteStep.append(conditional_step3.__dict__)
        advisor.steps.append(conditional_step1.__dict__)
        # print(advisor.__dict__)
        #
        advisor1 = json.dumps(advisor.__dict__)
        # print(advisor1)
        return advisor1
