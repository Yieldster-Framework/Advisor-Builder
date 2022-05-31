import json

import null

from Models.Advisor import Advisor
from Models.ConditionalStep import ConditionalStep
from Models.MoveStep import MoveStep

from SdkServiceApi import sdkApi


# @zope.interface.implementer(Service.HelloWorldService)
class HelloWorldImpl:

    def generate_maximize_asset_return(self):
        sdk = sdkApi()
        currentTokenData = sdk.get_token_price(token_address="0xdAC17F958D2ee523a2206206994597C13D831ec7",
                                               is_vault_token=False)
        previousTokenData = sdk.get_token_price(token_address="0xdAC17F958D2ee523a2206206994597C13D831ec7",
                                                is_vault_token=False,
                                                time_stamp="1653974358", is_date=False)

        if currentTokenData['status_code'] == 200 and previousTokenData['status_code'] == 200:
            currentTokenDataMap = currentTokenData['data']
            previousTokenDataMap = previousTokenData['data']
            currentTokenPrice = currentTokenDataMap['Token Price']
            previousTokenPrice = previousTokenDataMap['Token Price']
            priceDifferencePercentage = ((currentTokenPrice - previousTokenPrice) / previousTokenPrice) * 100
            if priceDifferencePercentage > 0.5:
                advisor = Advisor()
                advisor.advisorType = "Sample_Advisor"
                movestep = MoveStep()
                movestep.toAsset = "0xdAC17F958D2ee523a2206206994597C13D831ec7"
                movestep.fromAsset = "AD"
                advisor.steps.append(movestep.__dict__)
                return json.dumps(advisor.__dict__)
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
        advisor1 = json.dumps(advisor.__dict__)
        return advisor1
