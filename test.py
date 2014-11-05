#!/usr/bin/env python
# -*- coding: utf-8 -*-

import sys

def main():
    good_lines = open(sys.argv[1]).read().split('\n')
    test_lines = open(sys.argv[2]).read().split('\n')

    tp = 0
    tn = 0
    fp = 0
    fn = 0

    for index in xrange(len(good_lines)):
        good_line = good_lines[index]
        good_splited = good_line.split()
        test_splited = test_lines[index].split()

        if len(good_splited) == len(test_splited):
            for i in xrange(len(good_splited)):
                good_tag = good_splited[i].split('_')[1]
                test_tag = test_splited[i].split('_')[1]

                if good_splited[i] == test_splited[i]:
                    if good_tag == "PERSON":
                        tp += 1
                    else:
                        tn += 1
                else:
                    if good_tag == "PERSON":
                        fn += 1
                    else:
                        fp += 1
    precision = float(tp) / float(tp + fp)
    recall = float(tp) / float(tp + fn)

    print "precision: " + str(precision)
    print "recall: " + str(recall)

if __name__ == "__main__":
    main()
