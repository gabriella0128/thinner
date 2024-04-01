#!/bin/bash

docker rm -f thinner || true

docker run -itd -p "8081:8080" --name thinner thinner:1