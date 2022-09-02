package groovyScript

def static validatePageParam(int currentPage,int pageSize) {
    if(currentPage < 0 || pageSize <= 0){
        return false
    }
    return true
}