#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
import re

MIN_LENGTH = 3

def fix_line(line):
    result_line = line
    result_list = re.findall("^[A-Z ]+[:.,;] ", line)
    for result in result_list:
        result_line = result_line.replace(result, "")

    result_line = result_line.replace("  ", " ")
    return result_line

def check_line(line):
    if " pH " in line:
        return ""
    if "%" in line:
        return ""
    if ";" in line:
        return ""
    if len(re.findall("   "), line) > 0):
        return ""
    if line.count(" ") < MIN_LENGTH:
        return ""
    return line

def main():
    for line in open(sys.argv[1]).read().split('\n'):
        new_line = check_line(new_line)
        new_line = fix_line(line)
        if new_line != "":
            print new_line

if __name__ == "__main__":
    main()
