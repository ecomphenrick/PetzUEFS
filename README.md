Aqui está uma versão mais simples e direta do README.md para o seu projeto PetzUEFS, focado nos pontos essenciais do trabalho universitário.

🐾 PetzUEFS - Sistema de Apoio à Proteção de Animais da UEFS
📄 Sobre o Projeto
O PetzUEFS é uma aplicação desenvolvida em Java para a disciplina EXA 863 - MI - Programação da Universidade Estadual de Feira de Santana (UEFS).



O objetivo é criar uma solução digital para substituir o controle manual dos animais que circulam no campus (como gatos e cachorros). O sistema visa apoiar o monitoramento, os cuidados veterinários, a alimentação e a adoção desses animais.





✨ Funcionalidades Principais
O sistema permite o gerenciamento completo (CRUD - Create, Read, Update, Delete)  das seguintes entidades:

1.
Animais 

Dados: ID Único (Obrigatório) , Nome, Espécie (gato, cachorro, outro), Raça, Idade aproximada (não negativa) , Sexo, Situação atual (em observação/disponível para adoção/em tratamento).




2.
Setor Responsável 

Dados: ID Único (Obrigatório), Nome, Endereço.


3.
Pessoa Tutora 

Dados: ID Único (Obrigatório) , Nome, Endereço, Telefone (com validação) , E-mail (com validação).


Relatórios de Gestão 

Relação completa de Animais.

Relação de Animais agrupada por Setor Responsável.

Relação de Animais agrupada por Pessoa Tutora.

🏗️ Requisitos de Desenvolvimento
O projeto seguiu rigorosamente os seguintes padrões:


Linguagem: Java 


Arquitetura: Padrão Model-View-Controller (MVC).


Persistência: Json


Documentação: Uso do padrão Javadoc para documentação de classes, atributos e métodos.
