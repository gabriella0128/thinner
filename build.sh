#!/bin/bash

docker build -f Dockerfile --build-arg buildVersion=1.0.0 -t thinner:1 .