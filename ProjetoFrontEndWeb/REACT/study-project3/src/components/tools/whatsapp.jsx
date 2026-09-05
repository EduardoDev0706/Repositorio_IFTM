import { FloatingWhatsApp } from 'react-floating-whatsapp';

function Whatsapp () {
    return ( 
        <>
            <FloatingWhatsApp 
                phoneNumber='+5534984078584' 
                accountName='Aprenda React em 7 dias' 
                chatMessage='Seja bem-vindo a nossa equipe de suporte. Informe a sua mensagem abaixo:' 
                statusMessage='' 
                placeholder='Digite a sua mensagem' 
                allowClickAway={true} 
                allowEsc={true}
            />
        </>
     );
}

export default Whatsapp;