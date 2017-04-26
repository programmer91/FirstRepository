function checkDecimal(myForm) {
    
    if( /^\s*(\+|-)?((\d+(\.\d+)?)|(\.\d+))\s*$/.test(myForm)){
        
        return(true)
    }
    alert("Enter Decimal Number")
    return(false)
}