#!/bin/bash

site="http://www.web2fb2.net/"
s=$site"?ajax=1&autodetect=1&font=1&_=1"

while getopts ":i:t:u:" opt; do
case $opt in
  i)
    #echo "-i was triggered, Parameter: $OPTARG" >&2
    s=$s"&img=1"
    ;;
  t)
    #echo "-t was triggered, Parameter: $OPTARG" >&2
    s=$s"&tab=1"
    ;;
  u)
    #echo "-u was triggered, Parameter: $OPTARG" >&2
    s=$s"&url=$OPTARG" >&2
    ;;
esac
done
echo $site`curl -s $s | perl -ne 'if(/href\ =\ \047(.*?\.fb2\.zip)\047/) {print "$1"."\n";}'`
