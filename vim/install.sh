#!/usr/bin/env bash


#=============================================================================
# install.sh --- bootstrap script for XiaomoVim
# Copyright (c) 2017-2019 xiaomo & Contributors
# Author: xiaomo <xiaomo@xiaomo.info >
# URL: https://xiaomo.info
# License: MIT license
#=============================================================================

# Reset
Color_off='\033[0m'       # Text Reset
Version='0.6.0'

# Regular Colors
Red='\033[0;31m'
Blue='\033[0;34m'
Green='\033[0;32m'

#System name
System="$(uname -s)"

# 需要 某个命令
need_cmd () {
    if ! hash "$1" &>/dev/null; then
        error "Need '$1' (command not fount)"
        exit 1
    fi
}

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
    if [[ -d "$HOME/XiaomoMac" ]]; then
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
    if [[ -f "$HOME/.vimrc" ]]; then
        mv "$HOME/.vimrc" "$HOME/.vimrc_back"
        success "Backup $HOME/.vimrc to $HOME/.vimrc_back"
    fi

    if [[ -d "$HOME/.vim" ]]; then
        if [[ "$(readlink $HOME/.vim)" =~ \XiaomoMac$ ]]; then
            success "Installed XiaomoMac for vim"
        else
            mv "$HOME/.vim" "$HOME/.vim_back"
            success "BackUp $HOME/.vim to $HOME/.vim_back"
            ln -s "$HOME/XiaomoMac/vim/.vim" "$HOME/.vim"
            success "Installed XiaomoVim for vim"
        fi
    else
        ln -s "$HOME/XiaomoMac/vim/.vim" "$HOME/.vim"
        success "Installed XiaomoVim for vim"
    fi
}

uninstall_vim () {
    if [[ -d "$HOME/.vim" ]]; then
        if [[ "$(readlink $HOME/.vim)" =~ \XiaomoMac$ ]]; then
            rm "$HOME/.vim"
            success "Uninstall XiaomoVim for vim"
            if [[ -d "$HOME/.vim_back" ]]; then
                mv "$HOME/.vim_back" "$HOME/.vim"
                success "Recover from $HOME/.vim_back"
            fi
        fi
    fi
    if [[ -f "$HOME/.vimrc_back" ]]; then
        mv "$HOME/.vimrc_back" "$HOME/.vimrc"
        success "Recover from $HOME/.vimrc_back"
    fi
}


check_requirements () {
    info "Checking Requirements for XiaomoVim"
    if hash "git" &>/dev/null; then
        git_version=$(git --version)
        success "Check Requirements: ${git_version}"
    else
        warn "Check Requirements : git"
    fi
    if hash "vim" &>/dev/null; then
        is_vim8=$(vim --version | grep "Vi IMproved 8.0")
        is_vim74=$(vim --version | grep "Vi IMproved 7.4")
        if [ -n "$is_vim8" ]; then
            success "Check Requirements: vim 8.0"
        elif [ -n "$is_vim74" ]; then
            success "Check Requirements: vim 7.4"
        else
            if hash "nvim" &>/dev/null; then
                success "Check Requirements: nvim"
            else
                warn "XiaomoVim need vim 7.4 or above"
            fi
        fi
        if hash "nvim" &>/dev/null; then
            success "Check Requirements: nvim"
        fi
    else
        if hash "nvim" &>/dev/null; then
            success "Check Requirements: nvim"
        else
            warn "Check Requirements : vim or nvim"
        fi
    fi
    info "Checking true colors support in terminal:"
    sh -c "$(curl -fsSL https://raw.githubusercontent.com/JohnMorales/dotfiles/master/colors/24-bit-color.sh)"
}

usage () {
    echo "XiaomoVim install script : V ${Version}"
    echo ""
    echo "Usage : curl -sLf https://XiaomoVim.org/install.sh | bash -s -- [option] [target]"
    echo ""
    echo "  This is bootstrap script for XiaomoVim."
    echo ""
    echo "OPTIONS"
    echo ""
    echo " -i, --install            install XiaomoVim for vim or neovim"
    echo " -v, --version            Show version information and exit"
    echo " -u, --uninstall          Uninstall XiaomoVim"
    echo " -c, --checkRequirements  checkRequirements for XiaomoVim"
    echo ""
    echo "EXAMPLE"
    echo ""
    echo "    Install XiaomoVim for vim"
    echo ""
    echo "        curl -sLf https://github.com/xiaomoinfo/XiaomoMac/vim/install.sh | bash"
    echo ""
    echo "    Install XiaomoVim for vim only  only"
    echo ""
    echo ""
    echo "    Uninstall XiaomoVim"
    echo ""
}



download_font () {
    url="https://raw.githubusercontent.com/wsdjeg/DotFiles/master/local/share/fonts/$1"
    path="$HOME/.local/share/fonts/$1"
    if [[ -f "$path" ]]
    then
        success "Downloaded $1"
    else
        info "Downloading $1"
        wget -q -O "$path" "$url"
        success "Downloaded $1"
    fi
}

install_fonts () {
    if [[ ! -d "$HOME/.local/share/fonts" ]]; then
        mkdir -p $HOME/.local/share/fonts
    fi
    download_font "DejaVu Sans Mono Bold Oblique for Powerline.ttf"
    download_font "DejaVu Sans Mono Bold for Powerline.ttf"
    download_font "DejaVu Sans Mono Oblique for Powerline.ttf"
    download_font "DejaVu Sans Mono for Powerline.ttf"
    download_font "DroidSansMonoForPowerlinePlusNerdFileTypesMono.otf"
    download_font "Ubuntu Mono derivative Powerline Nerd Font Complete.ttf"
    download_font "WEBDINGS.TTF"
    download_font "WINGDNG2.ttf"
    download_font "WINGDNG3.ttf"
    download_font "devicons.ttf"
    download_font "mtextra.ttf"
    download_font "symbol.ttf"
    download_font "wingding.ttf"
    echo -n "Updating font cache... "
    if [ $System == "Darwin" ];then
        if [ ! -e "$HOME/Library/Fonts" ];then
            mkdir "$HOME/Library/Fonts"
        fi
        cp $HOME/.local/share/fonts/* $HOME/Library/Fonts/
    else
        fc-cache -fv
        mkfontdir "$HOME/.local/share/fonts"
        mkfontscale "$HOME/.local/share/fonts"
    fi

    echo "done"
}

if [ $# -gt 0 ]
then
    case $1 in
        --uninstall|-u)
            info "Trying to uninstall XiaomoVim"
            uninstall_vim
            exit 0
            ;;
        --checkRequirements|-c)
            check_requirements
            exit 0
            ;;
        --install|-i)
            need_cmd 'git'
            fetch_repo
            if [ $# -eq 2 ]
            then
                case $2 in
                    neovim)
                        exit 0
                        ;;
                    vim)
                        install_vim
                        exit 0
                esac
            fi
            install_vim
            exit 0
            ;;
        --help|-h)
            usage
            exit 0
            ;;
        --version|-v)
            msg "${Version}"
            exit 0
    esac
fi
# if no argv, installer will install XiaomoVim
need_cmd 'git'
fetch_repo
install_vim
install_fonts
