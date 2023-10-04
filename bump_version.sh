#!/usr/bin/env bash

# Find appVersionCode in gradle/libs.versions.toml
patchVersionCode=`grep "$1" gradle/libs.versions.toml | tr -dc '0-9'`
echo "Old version code: $patchVersionCode"

# Bump!
bumpedPatchVersionCode=$((patchVersionCode + 1))
echo "New version code: $bumpedPatchVersionCode"

# Replace appVersionCode in gradle/libs.versions.toml
echo "Replacing...."
sed -E "s/$1 = \"([0-9]+)\"/$1 = \"$bumpedPatchVersionCode\"/" gradle/libs.versions.toml > gradle/libs.versions.toml.temp
mv gradle/libs.versions.toml.temp gradle/libs.versions.toml
echo "Done!"
printf "\n"
