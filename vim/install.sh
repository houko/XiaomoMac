#!/usr/bin/env bash

#=============================================================================
# install.sh --- bootstrap script for XiaomoVim
# Copyright (c) 2017-2019 xiaomo & Contributors
# Author: xiaomo <xiaomo@xiaomo.info >
# URL: https://xiaomo.info
# License: MIT license
#=============================================================================

# Reset
Color_off='\033[0m'
Version='0.6.0'

# Regular Colors
Red='\033[0;31m'
Blue='\033[0;34m'
Green='\033[0;32m'

#System name
System="$(uname -s)"

# 消息
msg() {
    printf '%b\n' "$1" >&2
}

# 成功
success() {
    msg "${Green}[✔]${Color_off} ${1}${2}"
}

# 提示
info() {
    msg "${Blue}==>${Color_off} ${1}${2}"
}

# 错误
error() {
    msg "${Red}[✘]${Color_off} ${1}${2}"
    exit 1
}

# 警告
warn () {
    msg "${Red}[✘]${Color_off} ${1}${2}"
}

# 拉取仓库
fetch_repo () {
    if [ -d "$HOME/XiaomoMac" ]; then
        info "Trying to update XiaomoMac"
        git --git-dir "$HOME/XiaomoMac/.git" pull
        success "Successfully update XiaomoMac"
    else
        info "Trying to clone XiaomoVim"
        git clone https://github.com/xiaomoinfo/XiaomoMac.git "$HOME/XiaomoMac"
        success "Successfully clone XiaomoMac"
    fi
}

install_vim () {
    if [ -f "$HOME/.vimrc" ]; then
        mv "$HOME/.vimrc" "$HOME/.vimrc_back"
        success "Backup $HOME/.vimrc to $HOME/.vimrc_back"
    fi

    if [ -d "$HOME/.vim" ]; then
        if [ "$(readlink $HOME/.vim)" =~ \XiaomoMac$ ]; then
            success "Installed XiaomoVim for vim"
        else
            mv "$HOME/.vim" "$HOME/.vim_back"
            success "BackUp $HOME/.vim to $HOME/.vim_back"
            ln -s "$HOME/XiaomoMac/vim/.vim" "$HOME/.vim"

            if [ -d "$HOME/.vim/bundle/vundle" ]; then
                git clone https://github.com/gmarik/vundle.git ~/.vim/bundle/vundle
            fi

            success "Installed XiaomoVim for vim"
        fi
    else
        ln -s "$HOME/XiaomoMac/vim/.vim" "$HOME/.vim"
        success "Installed XiaomoVim for vim"
    fi
}

uninstall_vim () {
    if [ -d "$HOME/.vim" ]; then
        if [ "$(readlink $HOME/.vim)" =~ \XiaomoMac$ ]; then
            rm "$HOME/.vim"
            success "Uninstall XiaomoVim for vim"
            if [ -d "$HOME/.vim_back" ]; then
                mv "$HOME/.vim_back" "$HOME/.vim"
                success "Recover from $HOME/.vim_back"
            fi
        fi
    fi
    if [ -f "$HOME/.vimrc_back" ]; then
        mv "$HOME/.vimrc_back" "$HOME/.vimrc"
        success "Recover from $HOME/.vimrc_back"
    fi
}

fetch_repo
install_vim
