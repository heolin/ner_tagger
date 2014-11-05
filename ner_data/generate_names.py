#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys
from random import random

female_names = []
male_names = []
female_titles = []
male_titles = []
surnames = []

def get_female_name():
    t_id = int(random() * len(female_titles))
    n_id = int(random() * len(female_names))
    s_id = int(random() * len(surnames))

    if random() > 0.3:
        return "{0} {1} {2}".format(female_titles[t_id], female_names[n_id], surnames[s_id])
    return "{2}, {0} {1}".format(female_titles[t_id], female_names[n_id], surnames[s_id])


def get_male_name():
    t_id = int(random() * len(male_titles))
    n_id = int(random() * len(male_names))
    s_id = int(random() * len(surnames))

    if random() > 0.3:
        return "{0} {1} {2}".format(male_titles[t_id], male_names[n_id], surnames[s_id])
    return "{2}, {0} {1}".format(male_titles[t_id], male_names[n_id], surnames[s_id])


def get_name():
    if random() > 0.5:
        return get_female_name()
    return get_male_name()

def main():
    global female_names
    global male_names
    global female_titles
    global male_titles
    global surnames

    female_names = open(sys.argv[1]).read().split('\n')
    male_names = open(sys.argv[2]).read().split('\n')
    female_titles = open(sys.argv[3]).read().split('\n')
    male_titles = open(sys.argv[4]).read().split('\n')
    surnames = open(sys.argv[5]).read().split('\n')

    for _ in xrange(5000):
        print get_name()


if __name__ == "__main__":
    main()
