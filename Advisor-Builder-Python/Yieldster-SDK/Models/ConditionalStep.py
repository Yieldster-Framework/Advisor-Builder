from Models.Step import Step


class ConditionalStep(Step):
    def __init__(self):
        self.conditionalStatement = None
        self.trueExecuteStep = []
        self.falseExecuteStep = []
        self.type = "ConditionalStep"

    # def add_conditionalStatement(self, conditionalStatement):
    #     self.conditionalStatement = conditionalStatement
    #
    # def add_trueExecuteStep(self, trueExecuteStep):
    #     self.trueExecuteStep.append(trueExecuteStep)
    #
    # def add_falseExecuteStep(self, falseExecuteStep):
    #     self.falseExecuteStep.append(falseExecuteStep)
