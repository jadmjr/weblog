import axios, { AxiosRequestConfig } from 'axios';
import { useNavigate } from 'react-router-dom';
import { BASE_URL } from 'utils/requests';
import './styles.css';
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'



/* 
type Props = {
    movieId: String;
} */

function RecipientForm() {

    const navigate = useNavigate();

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const name = (event.target as any).name.value
        const email = (event.target as any).email.value;
        const phone = (event.target as any).phone.value
        const documentNumber = (event.target as any).documentNumber.value
        
        const streetName = (event.target as any).streetName.value
        const number = (event.target as any).number.value
        const complement = (event.target as any).complement.value
        const city = (event.target as any).city.value        
        const estate = (event.target as any).estate.value;
        const zipCode = (event.target as any).zipCode.value;
        const country = (event.target as any).country.value;

        const config: AxiosRequestConfig = {
            baseURL: BASE_URL,
            method: 'POST',
            url: '/recipient',
            data: {
                name: name,
                email: email,
                phone: phone,
                documentNumber: documentNumber,
                recipientAdress:{
                    streetName: streetName,
                    number: number, 
                    complement: complement, 
                    city: city, 
                    estate: estate, 
                    zipCode: zipCode, 
                    country: country
                }
             
            }
        }

        axios(config).then(response => {
            navigate("/");
        });
    }

    return (
        <div className="eucalipto-form-container">

            <div className="eucalipto-card-bottom-container">
                <h3>Digite os dados do Destinatário</h3>

                {/* <img className="eucalipto-movie-card-image" src={"https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/5eeea355389655.59822ff824b72.gif"} alt={"Yggdrasil"} /> */}

                <form className="eucalipto-form" onSubmit={handleSubmit}>
                    <div className="form-group eucalipto-form-group">

                        <label htmlFor="name">Nome</label>
                        <input type="text" className="form-control" id="name" />

                        <label htmlFor="email">E-mail</label>
                        <input type="email" className="form-control" id="email" />

                        <label htmlFor="phone">Informe seu telefone</label>
                        <PhoneInput
                            inputProps={{
                                name: 'phone',
                                required: true,
                                autoFocus: true
                            }}
                            onlyCountries={['br']}
                            masks={{ br: '(..) .....-....' }}
                            country='br'
                            regions={'america'}
                            inputClass={"bubu"}


                        />

                        <label htmlFor="documentNumber">CPF</label>
                        <input type="number" className="form-control" id="documentNumber" />

                        <br></br>
                        <h3>Informe o endereço do Destinatário</h3>

                        <label htmlFor="zipCode">CEP</label>
                        <input type="text" className="form-control" id="zipCode" />

                        <label htmlFor="number">Número</label>
                        <input type="text" className="form-control" id="number" />

                        <label htmlFor="streetName">Logradouro</label>
                        <input type="text" className="form-control" id="streetName" />


                        <label htmlFor="complement">Complemento</label>
                        <input type="text" className="form-control" id="complement" />

                        <label htmlFor="city">Cidade</label>
                        <input type="text" className="form-control" id="city" />

                        <label htmlFor="estate">Estado</label>
                        <input type="text" className="form-control" id="estate" />

                        <label htmlFor="country">Pais</label>
                        <input type="text" className="form-control" id="country" />








                    </div>


                    <div className="eucalipto-form-btn-container">
                        <button type="submit" className="btn btn-primary eucalipto-btn">Avançar</button>
                    </div>
                </form >
            </div >
        </div >
    );
}

export default RecipientForm;