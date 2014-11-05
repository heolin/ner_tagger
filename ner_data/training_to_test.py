#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys

def main():
    text = ""
    for line in open(sys.argv[1]).read().split('\n'):
        splited = line.split('\t')
        if splited[0] == "":
            text += "\n"
        else:
            text += "_".join(splited) + " "

    print text
if __name__ == "__main__":
    main()
