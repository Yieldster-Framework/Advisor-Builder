import string

import zope as zope


class HelloWorldService(zope.interface.Interface):
    def generateMaximizeAssetReturn(self) -> string:
        pass
