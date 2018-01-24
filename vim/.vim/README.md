# 打造小莫的个人vim
每个人使用vim都有自己的使用习惯，所以每个人的配置文件注定是不一样的，而且喜欢使用vim的人肯定都想折腾着配置一套自己的vim。但是，尽管配置不尽相同，但是配置规则是统一的，所以当看到别人的配置时可能会发出感慨。啊，原来还有这种骚操作，vim还能这样玩啊之类的。so,让我们一起加油吧，打造一个属于自己的vim吧。

# 备份原来的vim配置
```
mv ~/.vim ~/.vim.bak
mv ~/.vimrc ~/.vimrc.bak
```

# 下载我的vim配置
```
git clone https://github.com/xiaomoinfo/XiaomoMac.git ~/.
ln -s ~/XiaomoMac/.vim/vimrc ~/.vimrc
```

## 设置Bundle:
```
git clone https://github.com/gmarik/vundle.git ~/XiaomoMac/.vim/bundle/vundle
```

## 安装 bundles
由git管理的vim插件：在终端使用vim 打开任意文本文件，之后在vim的命令状态下 run:`:BundleInstall`

## 使用bundle:使用命令如下
- 1: `:BundleClean` 清理所有失效或无用的插件
- 2: `:BundleInstall` 安装新添加的vim插件
- 3: `:BundleInstall!` 更新所有插件
