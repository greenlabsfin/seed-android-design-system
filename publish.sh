#!/bin/bash

title="Maven publish script"
prompt="Select option: "
options=("core" "component" "all")

function publishMaven() {
  local module=$1
  echo "Publish $module to maven"
  ./gradlew "$module:publishReleasePublicationToSonatypeRepository"
}

echo "$title"
echo
PS3="$prompt"

select module in "${options[@]}" "Quit"; do
  case $module in
  "core")
    publishMaven "core-compose"
    break
    ;;
  "component")
    publishMaven "component-compose"
    break
    ;;
  "all")
    publishMaven "core-compose"
    publishMaven "component-compose"
    break
    ;;
  "Quit")
    break
    ;;
  esac
done
