package com.api.ady.pdfGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ady.cliente.Cliente;
import com.api.ady.cliente.ClienteRepository;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    }
