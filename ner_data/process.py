#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
from random import random

names = []


def get_name():
    index = int(random() * len(names))
    return names[index]


def process(sentence):
    while "[PERSON]" in sentence:
        name = " ".join(["<PERSON>"+token+"</PERSON>" for token in get_name().split()])
        sentence = sentence.replace("[PERSON]", name, 1)
    return sentence

def main():
    global names

    names = open(sys.argv[1]).read().split('\n')
    sentences = open(sys.argv[2]).read().split('\n')

    for sentence in sentences:
        print process(sentence)

if __name__ == "__main__":
    main()
