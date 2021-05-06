#!/bin/bash

# this script was tested on a raspberry pi and worked fine

#install ruby 3.0.1
sudo apt purge ruby -y && sudo apt update -y && sudo apt upgrade -y && sudo apt autoremove -y
sudo apt install git curl libssl-dev libreadline-dev zlib1g-dev autoconf bison build-essential libyaml-dev libreadline-dev libncurses5-dev libffi-dev libgdbm-dev -y
curl -sL https://raw.githubusercontent.com/rbenv/rbenv-installer/main/bin/rbenv-installer | bash -
echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc
echo 'eval "$(rbenv init -)"' >> ~/.bashrc
exec $SHELL
rbenv install -l
rbenv install 3.0.1
rbenv global 3.0.1
ruby -v

#install rails
sudo apt install sqlite3 npm yarn -y
gem install rails
rails --version
echo "done"
