package com.example.demo;

//import com.example.demo.dto.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

//import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Map<Long, UserDto> userStorage = new HashMap<>();
    private long currentId = 1;

    @Operation(summary = "Создать нового пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно создан")
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        userDto.setId(currentId++);
        userStorage.put(userDto.getId(), userDto);
        return ResponseEntity.ok(userDto);
    }

    @Operation(summary = "Обновить существующего пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пользователь успешно обновлен"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto UserDto) {
        if (!userStorage.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        UserDto.setId(id);
        userStorage.put(id, UserDto);
        return ResponseEntity.ok(UserDto);
    }

    @Operation(summary = "Удалить пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Пользователь успешно удален"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userStorage.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        userStorage.remove(id);
        return ResponseEntity.noContent().build();
    }
}