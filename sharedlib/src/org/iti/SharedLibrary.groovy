#!/usr/bin/env groovy

package org.iti

import org.iti.ops.ImageOps
import org.iti.ops.MavenOps

class SharedLibrary implements Serializable {
    def steps
    def imageOps
    def mavenOps
    
    SharedLibrary(steps) {
        this.steps = steps
        this.imageOps = new ImageOps(steps)
        this.mavenOps = new MavenOps(steps)
    }
    
    def getImageOps() {
        return imageOps
    }
    
    def getMavenOps() {
        return mavenOps
    }
    
    def getSteps() {
        return steps
    }
}
