import os

from yaml import load, Loader

basedir = os.path.abspath(os.path.dirname(__file__))

stream = open('config.yaml', 'r')
default_config = load(stream, Loader=Loader)


class Config(object):
    JAVA_API_URL = default_config.get('JAVA_API_URL')