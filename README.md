# Pacotes Turísticos

<table>
	<tr>
		<td>
			<img src="https://github.com/marcelorvergara/Pacotes-Turisticos/blob/main/app/imgs/pacotes.jpeg" alt="Lista de pacotes"> 
		</td>
		<td>
			<img src="https://github.com/marcelorvergara/Pacotes-Turisticos/blob/main/app/imgs/novo_pacote.jpeg" alt="Cadastro de pacote">
		</td>
		<td>
			<img src="https://github.com/marcelorvergara/Pacotes-Turisticos/blob/main/app/imgs/detalhes.jpeg" alt="Detalhes de um pacote">
		<td>
	</tr>
</table>	

### O que esse app faz?

- Esse app foi desenvolvido em 48 horas como parte de um desafio para uma vaga de estágio. A especificação era mostrar uma lista de pacotes turísticos com nome , valor e foto pacote e, ao clicar em um pacote, ir para o detalhe dele. No detalhe, além das informações da página inicial, deveria aparecer uma descrição do pacote.
- O desafio informava que poderia ser feito um mock de dados, porém foi feita a opção de criar uma base de dados no Firebase Firestore e autenticação no Firebase Auth para que um usuário administrativo pudesse cadastrar as informações.

### Como foi desenvolvido?

- Feito inteiramente no Android Studio, o app foi feito obedecendo o prazo de 48 horas e disponibilizado inicialmente no bitbucket. Ele consiste de uma tela inicial com um botão para o acesso restrito, que leva ao login para cadastro de pacotes e, também na tela inicial, um recycler view para mostrar a lista de pacotes cadastrados.
- As imagens dos pacotes são cadastradas no Firebase Storage e um registro com as informações no Firebase Firestore.
- Foi feito com uma activity e algun fragments navegando com um NavHost.

### O que falta?

- O app foi enviado para a recrutadora e estou aguardando a resposta :0

### Licença

- Esse sistema é licenciado através de uma licença MIT. Veja <a href=https://github.com/marcelorvergara/Pacotes-Turisticos/blob/main/LICENSE> aqui </a> para detalhes.
