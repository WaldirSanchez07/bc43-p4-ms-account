package com.nttdata.msaccount.application.service;

import com.nttdata.msaccount.application.dto.request.AssociateRequest;
import com.nttdata.msaccount.application.dto.response.ObjectResponse;
import io.reactivex.rxjava3.core.Maybe;

public interface AssociationExternalService {

  Maybe<ObjectResponse> associateAccounts(AssociateRequest request);

}
