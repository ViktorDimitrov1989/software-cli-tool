package org.generator

trait CliTest {
    void main(String args){
        ImageGeneratorCli.main(args.split(' '))
    }
}