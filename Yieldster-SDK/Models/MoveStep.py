from Models.Step import Step

class MoveStep(Step):
    def __init__(self):
        self.amount = None
        self.fromAsset = None
        self.toAsset = None
        self.type = "MoveStep"

    # def add_from_asset(self, from_asset):
    #     self.fromAsset = from_asset
    #
    # def add_to_asset(self, to_asset):
    #     self.toAsset = to_asset
    #
    # def amount(self, amount):
    #     self.amount = amount
