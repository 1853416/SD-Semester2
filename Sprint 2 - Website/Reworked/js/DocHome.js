window.addEventListener('load',()=> {
    const params =(new URL(document.location)).searchParams;
    const phone =params.get('phone');
    document.getElementById('drName').innerHTML=phone;
    

})
