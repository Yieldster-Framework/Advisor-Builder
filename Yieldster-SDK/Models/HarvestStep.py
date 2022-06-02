from Models.Step import Step


class HarvestStep(Step):
    def __init__(self):
        self.stakingContract = None
        self.returnTokens = []
        self.type = "HarvestStep"
