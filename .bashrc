# .bashrc

# User specific aliases and functions

alias rm='rm -i'
alias cp='cp -i'
alias mv='mv -i'
alias updatealias="vim ~/.bashrc"
alias reloadalias="source ~/.bashrc && source ~/.bash_profile"

# Source global definitions
if [ -f /etc/bashrc ]; then
	. /etc/bashrc
fi

IP=$(ifconfig eth0 | awk ' /inet addr:/  { print $2 } ' | cut -c6- )
if test -z "$IP"
then
  IP=$(hostname | awk -F. ' { print $1 } ')
fi

export IP
export PS1='\[\e[32m\]#\##\[\e[1;31m\]\u@\[\e[36m\]\A $IP \w]\$\[\e[m '

#set erlang environment
export PATH=$PATH:/usr/local/erlang/bin

#set maven environment
export MAVEN_HOME=/usr/local/maven-3.5.0/
export PATH=${MAVEN_HOME}/bin:${PATH}
#set java environment
export JAVA_HOME=/usr/java/jdk-9
export PATH=$JAVA_HOME/bin:$PATH
