
package com.fabio.pratica.controller;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.web.bind.annotation.*;

import com.fabio.pratica.dominio.User;
import com.fabio.pratica.dominio.Usuario;
import com.fabio.pratica.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController  {

    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @RequestBody User usuario) {
                 System.out.println("CHEGOU AO MÉTODO DE LOGIN");

        // Credenciais estáticas para fins de aprendizagem
        boolean autenticado =
                "fabio".equals(usuario.getName())
                && "fabio@gmail.com".equals(usuario.getEmail()); // Substitua por lógica de autenticação real em um cenário de produção

                

        if (!autenticado) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of(
                        "mensagem", "Não autorizado"
                    ));
        }

        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(3600);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("pratica-api")
                .subject(usuario.getName())
                .issuedAt(agora)
                .expiresAt(expiracao)
                .build();

        JwsHeader header = JwsHeader
                .with(MacAlgorithm.HS256)
                .build();

        String token = jwtEncoder
                .encode(JwtEncoderParameters.from(header, claims))
                .getTokenValue();

        return ResponseEntity.ok(Map.of(
                "token", token,
                "tipo", "Bearer",
                "expiraEm", "3600 segundos"
        ));
    }

    @PostMapping("/cadastrar")
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/listarUsuarios")
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
    @PutMapping("/atualizar/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }
    
    
    public void deletar(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
    




}
