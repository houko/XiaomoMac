# 打造小莫的个人vim

每个人使用vim都有自己的使用习惯，所以每个人的配置文件注定是不一样的，而且喜欢使用vim的人肯定都想折腾着配置一套自己的vim。但是，尽管配置不尽相同，但是配置规则是统一的，所以当看到别人的配置时可能会发出感慨。啊，原来还有这种骚操作，vim还能这样玩啊之类的。so,让我们一起加油吧，打造一个属于自己的vim吧。

# 备份原来的vim配置
```
mv ~/.vim ~/.vim.bak
mv ~/.vimrc ~/.vimrc.bak
```

# 下载我的vim配置
```
git clone https://github.com/xiaomoinfo/XiaomoVim.git ~/.vim
ln -s ~/.vim/vimrc ~/.vimrc
```

# 设置Bundle:
```
git clone https://github.com/gmarik/vundle.git ~/.vim/bundle/vundle
```

# 安装 bundles, 由git管理的vim插件：在终端使用vim 打开任意文本文件，之后在vim的命令状态下 run:`:BundleInstall`


# 使用bundle:使用命令如下
- 1: `:BundleClean` 清理所有失效或无用的插件
- 2: `:BundleInstall` 安装新添加的vim插件
- 3: `:BundleInstall!` 更新所有插件


# [license](LICENSE)

```
MIT License

Copyright (c) 2018 Peng Hu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```