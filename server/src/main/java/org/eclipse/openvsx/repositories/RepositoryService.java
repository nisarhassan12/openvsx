/********************************************************************************
 * Copyright (c) 2019 TypeFox and others
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 ********************************************************************************/
package org.eclipse.openvsx.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import org.eclipse.openvsx.entities.Extension;
import org.eclipse.openvsx.entities.ExtensionBinary;
import org.eclipse.openvsx.entities.ExtensionIcon;
import org.eclipse.openvsx.entities.ExtensionReadme;
import org.eclipse.openvsx.entities.ExtensionReview;
import org.eclipse.openvsx.entities.ExtensionVersion;
import org.eclipse.openvsx.entities.PersonalAccessToken;
import org.eclipse.openvsx.entities.Publisher;
import org.eclipse.openvsx.entities.PublisherMembership;
import org.eclipse.openvsx.entities.UserData;

@Component
public class RepositoryService {

    @Autowired PublisherRepository publisherRepo;
    @Autowired ExtensionRepository extensionRepo;
    @Autowired ExtensionVersionRepository extensionVersionRepo;
    @Autowired ExtensionBinaryRepository extensionBinaryRepo;
    @Autowired ExtensionIconRepository extensionIconRepo;
    @Autowired ExtensionReadmeRepository extensionReadmeRepo;
    @Autowired ExtensionReviewRepository extensionReviewRepo;
    @Autowired UserDataRepository userDataRepo;
    @Autowired PublisherMembershipRepository membershipRepo;
    @Autowired PersonalAccessTokenRepository tokenRepo;

    public Publisher findPublisher(String name) {
        return publisherRepo.findByName(name);
    }

    public Extension findExtension(String name, Publisher publisher) {
        return extensionRepo.findByNameAndPublisher(name, publisher);
    }

    public Extension findExtension(String name, String publisherName) {
        return extensionRepo.findByNameAndPublisherName(name, publisherName);
    }

    public Streamable<Extension> findExtensions(Publisher publisher) {
        return extensionRepo.findByPublisherOrderByNameAsc(publisher);
    }

    public Streamable<Extension> findAllExtensions() {
        return extensionRepo.findAll();
    }

    public ExtensionVersion findVersion(String version, Extension extension) {
        return extensionVersionRepo.findByVersionAndExtension(version, extension);
    }

    public ExtensionVersion findVersion(String version, String extensionName, String publisherName) {
        return extensionVersionRepo.findByVersionAndExtensionNameAndExtensionPublisherName(version, extensionName, publisherName);
    }

    public Streamable<ExtensionVersion> findVersions(Extension extension) {
        return extensionVersionRepo.findByExtension(extension);
    }

    public ExtensionBinary findBinary(ExtensionVersion extVersion) {
        return extensionBinaryRepo.findByExtension(extVersion);
    }

    public ExtensionIcon findIcon(ExtensionVersion extVersion) {
        return extensionIconRepo.findByExtension(extVersion);
    }

    public ExtensionReadme findReadme(ExtensionVersion extVersion) {
        return extensionReadmeRepo.findByExtension(extVersion);
    }

    public Streamable<ExtensionReview> findReviews(Extension extension) {
        return extensionReviewRepo.findByExtension(extension);
    }

    public long countReviews(Extension extension) {
        return extensionReviewRepo.countByExtension(extension);
    }

    public UserData findUser(String provider, String providerId) {
        return userDataRepo.findByProviderAndProviderId(provider, providerId);
    }

    public PublisherMembership findMembership(UserData user, Publisher publisher) {
        return membershipRepo.findByUserAndPublisher(user, publisher);
    }

    public Streamable<PersonalAccessToken> findAccessTokens(UserData user) {
        return tokenRepo.findByUser(user);
    }

    public PersonalAccessToken findAccessToken(String value) {
        return tokenRepo.findByValue(value);
    }

    public PersonalAccessToken findAccessToken(long id) {
        return tokenRepo.findById(id);
    }

}