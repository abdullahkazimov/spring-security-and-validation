package abdkzmv.wm2.assignment2.model.mapper;

import abdkzmv.wm2.assignment2.model.dto.UserDto;
import abdkzmv.wm2.assignment2.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper instance = Mappers.getMapper(UserMapper.class);

    List<UserDto> userListToUserDtoList(List<User> user);

    UserDto userToUserDto(User user);
}
