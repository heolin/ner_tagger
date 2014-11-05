#!/usr/bin/env bash
echo "STATISTICAL:"
python test.py ner_test.tagged ner_test_result.stat
echo "RULE-BASED:"
python test.py ner_test.tagged ner_test_result.rule
