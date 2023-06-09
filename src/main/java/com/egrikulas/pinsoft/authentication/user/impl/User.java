package com.egrikulas.pinsoft.authentication.user.impl;

import com.egrikulas.pinsoft.library.entity.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = User.TABLE)
public class User extends AbstractEntity {

    public static final String TABLE="t_user";
    public static final String COL_FIRST_NAME="first_name";
    public static final String COL_LAST_NAME="last_name";
    public static final String COL_USERNAME="username";
    public static final String COL_PASSWORD="password";
    public static final String COL_EMAIL="e_mail";
    public static final String COL_ROLE="role";
    public static final String COL_TOKEN="token";

    @Column(name = COL_FIRST_NAME)
    private String firstName;

    @Column(name = COL_LAST_NAME)
    private String lastName;

    @Column(name = COL_USERNAME)
    private String userName;

    @Column(name = COL_PASSWORD)
    private String password;

    @Column(name = COL_EMAIL)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = COL_ROLE)
    private Role role;

    @Column(name = COL_TOKEN)
    private String token;




}
