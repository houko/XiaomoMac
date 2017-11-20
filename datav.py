#!/usr/bin/python3
# coding=utf-8
"""
 把今天最好的表现当作明天最新的起点．．～
 いま 最高の表現 として 明日最新の始発．．～
 Today the best performance  as tomorrow newest starter!
 author: xiaomo
 github: https://github.com/syoubaku
 email: xiaomo@xiamoo.info
 QQ_NO: 83387856
 Date: 2017/11/20 10:22
 Description:
 Copyright(©) 2017 by xiaomo.
"""

from flask import Flask

app = Flask(__name__)


@app.route('/', methods=['GET', 'POST'])
def home():
    return '<h1>Home</h1>'


if __name__ == '__main__':
    app.run()
